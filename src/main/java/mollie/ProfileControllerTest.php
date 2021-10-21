<?php

namespace Tests\Integration\Controllers;

use Tests\Integration\WebTestCase;

class ProfileControllerTest extends WebTestCase
{
    public function testUpdate(): void
    {
        $user   = $this->getUserSpawner()->createRegularUser();
        $client = $this->createClientWithLoggedInUser($user);

        $client->request('POST', '/profile?id=' . $user->getId(), [
            'display_name' => 'New Display Name',
            'phone_number' => '+31612345678',
        ]);

        $responseBody = $client->getResponse()->getContents();

        self::assertStringContainsString('User: New Display Name', $responseBody);
        self::assertStringContainsString('Phone number: +31612345678', $responseBody);
    }
}